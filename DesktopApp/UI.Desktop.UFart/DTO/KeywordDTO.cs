using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UI.Desktop.UFart.UI.DTO.Base;

namespace UI.Desktop.UFart.UI.DTO
{
    public class KeywordDTO
        : EntityBaseDTO
    {
        public string Name { get; set; }
        public int PersonID { get; set; }
    }
}
