using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using UFart.Desktop.DataAccess.Repositories.Base.Interfaces;
using UFart.Desktop.Domain.Entity;
using UI.Desktop.DataAccess.DTO;

namespace UFart.Desktop.DataAccess.Repositories.WebApi
{
    public class WebApiPersonPageRankRepository
        : WebApiRepositoryBase<PersonPageRank>, IPersonPageRankRepository
    {
        public WebApiPersonPageRankRepository(string url)
            : base(url)
        {

        }

        public IEnumerable<RankWithPersonDTO> GetBySite(int idSite)
        {
            var reply = string.Empty;
            try
            {
                reply = client.DownloadString($"{url}total/{idSite.ToString()}");
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
            return JsonConvert.DeserializeObject<IEnumerable<RankWithPersonDTO>>(reply);
        }

        public IEnumerable<PersonPageRank> GetBy(
            int? idSite = null, 
            int? idPerson = null, 
            DateTime? fromDate = null, 
            DateTime? toDate = null, 
            int? skip = null, 
            int? take = null)
        {

            return new List<PersonPageRank>();
        }
    }
}
