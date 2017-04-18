using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UFart.Desktop.UI.DTO;

namespace UFart.Desktop.UI.FakeDataDTO
{
    public class FakeSitesListDTO
        : List<SiteDTO>
    {
        public FakeSitesListDTO()
        {
            AddRange(new List<SiteDTO> {
                new SiteDTO {ID = 1, Name = @"https://ria.ru/"},
                new SiteDTO {ID = 2, Name = @"https://lenta.ru/"}
            });
        }
    }
}
