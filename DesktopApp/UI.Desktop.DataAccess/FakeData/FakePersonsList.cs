using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UFart.Desktop.Domain.Entity;

namespace UFart.Desktop.DataAccess.FakeData
{
    public class FakePersonsList
        : List<Person>
    {
        public FakePersonsList()
        {
            AddRange(new List<Person> {
                new Person {ID = 1, Name = "Путин"},
                new Person {ID = 2, Name = "Медведев"},
                new Person {ID = 3, Name = "Шойгу"}
            });
        }
    }
}
