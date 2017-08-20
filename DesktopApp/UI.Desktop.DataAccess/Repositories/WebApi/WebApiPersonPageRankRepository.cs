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

        public IEnumerable<RankOnDateDTO> GetBy(
            int? idSite = null, 
            int? idPerson = null, 
            DateTime? fromDate = null, 
            DateTime? toDate = null, 
            int? skip = null, 
            int? take = null)
        {

            var reply = string.Empty;
            try
            {
                var fullUrl = string.Format("{0}?{1}&{2}&{3}&{4}",
                    $"{url}daily",
                    $"personId={((idPerson != null) ? idPerson.ToString() : "null")}",
                    $"siteId={((idSite != null) ? idSite.ToString() : "null")}",
                    $"startDate={((fromDate != null) ? fromDate.Value.Ticks.ToString() : "null")}",
                    $"finishDate={((toDate != null) ? toDate.Value.Ticks.ToString() : "null")}"
                    );

                reply = client.DownloadString(fullUrl);
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
            return JsonConvert.DeserializeObject<IEnumerable<RankOnDateDTO>>(reply);
        }
    }
}
