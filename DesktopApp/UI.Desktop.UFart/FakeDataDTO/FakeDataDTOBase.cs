using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UFart.Desktop.UI.FakeDataDTO
{
    public class FakeDataDTOBase
    {
        public FakeSitesListDTO Sites { get; set; }
        public FakePersonsListDTO Persons { get; set; }
        public FakePagesDTOList Pages { get; set; }

        public FakePersonPagesRankListDTO Ranks { get; set; }

        public FakeDataDTOBase()
        {
            Sites = new FakeSitesListDTO();
            Persons = new FakePersonsListDTO();

            Pages = new FakePagesDTOList();
            Pages.ForEach(p => p.Site = Sites.FirstOrDefault(s => s.ID == p.SiteID));

            Ranks = new FakePersonPagesRankListDTO();
            Ranks.ForEach(r => {
                r.Person = Persons.FirstOrDefault(p => p.ID == r.PersonID);
                r.Page = Pages.FirstOrDefault(p => p.ID == r.PageID);
            });


        }
    }
}
