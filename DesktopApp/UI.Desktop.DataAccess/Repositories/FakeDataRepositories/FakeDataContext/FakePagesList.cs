using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UFart.Desktop.Domain.Entity;

namespace UFart.Desktop.DataAccess.FakeData
{
    public class FakePagesList
        : List<Page>
    {
        public FakePagesList()
        {
            AddRange(new List<Page> {
                new Page {ID = 1, Url = @"https://ria.ru/0", SiteID = 1, FoundDateTime = DateTime.Now, LastScanDate = DateTime.Now },
                new Page {ID = 2, Url = @"https://ria.ru/1", SiteID = 1, FoundDateTime = DateTime.Now, LastScanDate = DateTime.Now },
                new Page {ID = 3, Url = @"https://ria.ru/2", SiteID = 1, FoundDateTime = DateTime.Now, LastScanDate = DateTime.Now },
                new Page {ID = 4, Url = @"https://ria.ru/3", SiteID = 1, FoundDateTime = DateTime.Now, LastScanDate = null },

                new Page {ID = 100, Url = @"https://lenta.ru/1", SiteID = 2, FoundDateTime = DateTime.Now, LastScanDate = DateTime.Now },
                new Page {ID = 101, Url = @"https://lenta.ru/2", SiteID = 2, FoundDateTime = DateTime.Now, LastScanDate = DateTime.Now },
                new Page {ID = 102, Url = @"https://lenta.ru/3", SiteID = 2, FoundDateTime = DateTime.Now, LastScanDate = DateTime.Now },
                new Page {ID = 103, Url = @"https://lenta.ru/4", SiteID = 2, FoundDateTime = DateTime.Now, LastScanDate = DateTime.Now },
                new Page {ID = 104, Url = @"https://lenta.ru/5", SiteID = 2, FoundDateTime = DateTime.Now, LastScanDate = DateTime.Now }
            });
        }
    }
}
