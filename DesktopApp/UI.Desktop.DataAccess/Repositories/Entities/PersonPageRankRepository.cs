using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UFart.Desktop.DataAccess.Repositories.Base;
using UFart.Desktop.DataAccess.Repositories.Base.Interfaces;
using UFart.Desktop.Domain.Entity;

namespace UFart.Desktop.DataAccess.Repositories.Entities
{
    public class PersonPageRankRepository
        : EntityRepositoryBase<PersonPageRank>, IPersonPageRankRepository
    {
        public PersonPageRankRepository()
            : base()
        {

        }

        public PersonPageRankRepository(List<PersonPageRank> fakeData)
            : base(fakeData) 
        {
        }

        public IEnumerable<PersonPageRank> GetBySite(int idSite)
        {
            if (fakeData != null)
            {
                return fakeData.FindAll(f=>
                    f.Page.SiteID == idSite
                    && f.Page.LastScanDate != null);
            }
            throw new NotImplementedException();
        }

        public IEnumerable<PersonPageRank> GetBy(
            int? idSite = null, 
            int? idPerson = null, 
            DateTime? fromDate = null, 
            DateTime? toDate = null, 
            int? skip = null, 
            int? take = null)
        {
            if (fakeData != null)
            {
                return fakeData.FindAll(f =>
                    ((idSite.HasValue) ? f.Page.SiteID == idSite : true)
                    && ((idPerson.HasValue) ? f.PersonID == idPerson : true)
                    && (((fromDate.HasValue) ? fromDate.Value.Date.CompareTo((f.Page.LastScanDate ?? DateTime.MinValue).Date) <= 0 : true)
                        && ((toDate.HasValue) ? toDate.Value.Date.CompareTo((f.Page.LastScanDate ?? DateTime.MinValue).Date) <= 0 : true))
                    && f.Page.LastScanDate.HasValue);
            }
            throw new NotImplementedException();
        }
    }
}
