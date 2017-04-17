using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UI.Desktop.UFart.UI.DTO;

namespace UI.Desktop.UFart.FakeData
{
    public class FakeSitesList
        : List<SiteDTO>
    {
        public FakeSitesList()
        {
            AddRange(new List<SiteDTO> {
                new SiteDTO {ID = 1, Name = @"https://ria.ru/"},
                new SiteDTO {ID = 2, Name = @"https://lenta.ru/"}
            });
        }
    }
}
