using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UI.Desktop.DataAccess.DTO
{
    [JsonObject(MemberSerialization.OptIn)]
    public class RankOnDateDTO
    {
        [JsonProperty("date")]
        public long DateTick { get; set; }

        public DateTime Date
        {
            get
            {
                return new DateTime(DateTick);
            }
        }

        [JsonProperty("rank")]
        public int Rank { get; set; }
    }
}
