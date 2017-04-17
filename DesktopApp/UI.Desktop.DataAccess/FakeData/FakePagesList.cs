using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UI.Desktop.UFart.UI.DTO;

namespace UI.Desktop.UFart.FakeData
{
    public class FakePagesList
        : List<PageDTO>
    {
        public FakePagesList()
        {
            AddRange(new List<PageDTO> {
                new PageDTO {ID = 1, Url = @"https://ria.ru/0", SiteID = 1, FoundDateTime = DateTime.Now, LastScanDate = DateTime.Now },
                new PageDTO {ID = 2, Url = @"https://ria.ru/1", SiteID = 1, FoundDateTime = DateTime.Now, LastScanDate = DateTime.Now },
                new PageDTO {ID = 3, Url = @"https://ria.ru/2", SiteID = 1, FoundDateTime = DateTime.Now, LastScanDate = DateTime.Now },
                new PageDTO {ID = 4, Url = @"https://ria.ru/3", SiteID = 1, FoundDateTime = DateTime.Now, LastScanDate = DateTime.Now },

                new PageDTO {ID = 100, Url = @"https://lenta.ru/1", SiteID = 2, FoundDateTime = DateTime.Now, LastScanDate = DateTime.Now },
                new PageDTO {ID = 101, Url = @"https://lenta.ru/2", SiteID = 2, FoundDateTime = DateTime.Now, LastScanDate = DateTime.Now },
                new PageDTO {ID = 102, Url = @"https://lenta.ru/3", SiteID = 2, FoundDateTime = DateTime.Now, LastScanDate = DateTime.Now },
                new PageDTO {ID = 103, Url = @"https://lenta.ru/4", SiteID = 2, FoundDateTime = DateTime.Now, LastScanDate = DateTime.Now },
                new PageDTO {ID = 104, Url = @"https://lenta.ru/5", SiteID = 2, FoundDateTime = DateTime.Now, LastScanDate = DateTime.Now }
            });
        }
    }
}
