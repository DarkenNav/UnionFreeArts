using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UFart.Desktop.UI.DTO.Base;

namespace UFart.Desktop.UI.DTO
{
    public class KeywordDTO
        : EntityBaseDTO
    {
        public string Name { get; set; }
        public int PersonID { get; set; }
    }
}
