using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UI.Desktop.UFart.UI.DTO.Base;

namespace UI.Desktop.UFart.UI.DTO
{
    public class PageDTO
        : EntityBaseDTO
    {
        public string Url { get; set; }
        public int SiteID { get; set; }
        public DateTime FoundDateTime { get; set; }
        public DateTime LastScanDate { get; set; }
    }
}
