using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UI.Desktop.UFart.UI.DTO;

namespace UI.Desktop.UFart.FakeData
{
    public class FakePersonsList
        : List<PersonDTO>
    {
        public FakePersonsList()
        {
            AddRange(new List<PersonDTO> {
                new PersonDTO {ID = 1, Name = "Путин"},
                new PersonDTO {ID = 2, Name = "Медведев"},
                new PersonDTO {ID = 3, Name = "Шойгу"}
            });
        }
    }
}
