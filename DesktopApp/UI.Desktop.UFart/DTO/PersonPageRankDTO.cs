using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UFart.Desktop.UI.DTO
{
    public class PersonPageRankDTO
    {
        public int PersonID { get; set; }
        public int PageID { get; set; }
        public int Rank { get; set; }

        public PageDTO Page { get; set; }
        public PersonDTO Person { get; set; }
    }
}
