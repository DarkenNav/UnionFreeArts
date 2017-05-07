using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UFart.Desktop.Domain.Entity;

namespace UFart.Desktop.DataAccess.FakeData
{
    public class FakeSitesList
        : List<Site>
    {
        public FakeSitesList()
        {
            AddRange(new List<Site> {
                new Site {ID = 1, Name = @"https://ria.ru/"},
                new Site {ID = 2, Name = @"https://lenta.ru/"}
            });
        }
    }
}
