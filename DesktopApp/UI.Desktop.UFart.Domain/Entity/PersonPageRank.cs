using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UFart.Desktop.Domain.Entity
{
    public class PersonPageRank
    {
        public int PersonID { get; set; }
        public int PageID { get; set; }
        public int Rank { get; set; }

        public Person Person { get; set; }
        public Page Page { get; set; }
    }
}
