using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UI.Desktop.UFart.Domain.Entity.Base;

namespace UI.Desktop.UFart.Domain.Entity
{
    public class Page
        : EntityBase
    {
        public string Url { get; set; }
        public int SiteID { get; set; }
        public DateTime FoundDateTime { get; set; }
        public DateTime LastScanDate { get; set; }
    }
}
