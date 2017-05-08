using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UFart.Desktop.UI.DTO.Base;

namespace UFart.Desktop.UI.DTO
{
    public class PageDTO
        : EntityBaseDTO
    {
        public string Url { get; set; }
        public int SiteID { get; set; }
        public DateTime FoundDateTime { get; set; }
        public DateTime? LastScanDate { get; set; }

        public SiteDTO Site { get; set; }
    }
}
