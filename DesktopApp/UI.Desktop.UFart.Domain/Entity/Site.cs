using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UFart.Desktop.Domain.Entity.Base;

namespace UFart.Desktop.Domain.Entity
{
    public class Site
        : EntityBase
    {
        public string Name { get; set; }

        public ICollection<Page> Pages { get; set; }
    }
}
