using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UI.Desktop.UFart.Domain.Entity.Base;

namespace UI.Desktop.UFart.Domain.Entity
{
    public class Keyword
        : EntityBase
    {
        public string Name { get; set; }
        public int PersonID { get; set; }
    }
}
