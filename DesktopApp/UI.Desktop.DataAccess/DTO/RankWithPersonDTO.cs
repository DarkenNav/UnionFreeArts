using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UI.Desktop.DataAccess.DTO
{
    [JsonObject(MemberSerialization.OptIn)]
    public class RankWithPersonDTO
    {
        [JsonProperty("name")]
        public string PersonName { get; set; }
        [JsonProperty("rank")]
        public int PersonRank { get; set; }
    }
}
