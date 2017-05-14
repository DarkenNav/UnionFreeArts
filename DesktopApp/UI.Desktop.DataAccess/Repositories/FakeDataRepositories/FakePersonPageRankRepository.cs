using AutoMapper;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UFart.Desktop.DataAccess.FakeData;
using UFart.Desktop.DataAccess.Repositories.Base;
using UFart.Desktop.DataAccess.Repositories.Base.Interfaces;
using UFart.Desktop.Domain.Entity;
using UI.Desktop.DataAccess.DTO;

namespace UFart.Desktop.DataAccess.Repositories.FakeDataRepositories
{
    public class FakePersonPageRankRepository
        : FakeDataRepositoryBase<PersonPageRank>, IPersonPageRankRepository
    {
        FakeDataBase fakeContext;
        public FakePersonPageRankRepository(FakeDataBase fakeContext)
            : base(fakeContext.Ranks) 
        {
            this.fakeContext = fakeContext;
        }

        public IEnumerable<RankWithPersonDTO> GetBySite(int idSite)
        {
            var stats = fakeContext.Ranks.FindAll(f => f.Page.SiteID == idSite && f.Page.LastScanDate != null);

            var result = new List<RankWithPersonDTO>();
            foreach (var p in fakeContext.Persons)
            {
                result.Add(new RankWithPersonDTO {
                    PersonName = p.Name,
                    PersonRank = stats.Where(s => s.PersonID == p.ID).Sum(s => s.Rank)
                });
            }

            return result;
        }

        public IEnumerable<RankOnDateDTO> GetBy(
            int? idSite = null, 
            int? idPerson = null, 
            DateTime? fromDate = null, 
            DateTime? toDate = null, 
            int? skip = null, 
            int? take = null)
        {

            var stats = fakeData.FindAll(f =>
                ((idSite.HasValue) ? f.Page.SiteID == idSite : true)
                && ((idPerson.HasValue) ? f.PersonID == idPerson : true)
                && (((fromDate.HasValue) ? fromDate.Value.Date.CompareTo((f.Page.LastScanDate ?? DateTime.MinValue).Date) <= 0 : true)
                    && ((toDate.HasValue) ? toDate.Value.Date.CompareTo((f.Page.LastScanDate ?? DateTime.MinValue).Date) <= 0 : true))
                && f.Page.LastScanDate.HasValue);

            return Mapper.Map<IEnumerable<RankOnDateDTO>>(stats);
        }
    }
}