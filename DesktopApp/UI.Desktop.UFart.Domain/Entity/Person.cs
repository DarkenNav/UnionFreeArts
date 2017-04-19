using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UFart.Desktop.Domain.Entity.Base;

namespace UFart.Desktop.Domain.Entity
{
    public class Person
        : EntityBase
    {
        public string Name { get; set; }

        public ICollection<Keyword> Keywords { get; set; }
        public ICollection<PersonPageRank> PersonPageRanks { get; set; }
    }
}
