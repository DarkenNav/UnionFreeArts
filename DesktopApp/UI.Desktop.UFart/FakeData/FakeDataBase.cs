using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UFart.Desktop.UI.FakeData
{
    public class FakeDataBase
    {
        public FakeSitesList Sites { get; set; }
        public FakePersonsList Persons { get; set; }
        public FakePagesList Pages { get; set; }

        public FakePersonPagesRankList Ranks { get; set; }

        public FakeDataBase()
        {
            Sites = new FakeSitesList();
            Persons = new FakePersonsList();

            Pages = new FakePagesList();
            Pages.ForEach(p => p.Site = Sites.FirstOrDefault(s => s.ID == p.SiteID));

            Ranks = new FakePersonPagesRankList();
            Ranks.ForEach(r => {
                r.Person = Persons.FirstOrDefault(p => p.ID == r.PersonID);
                r.Page = Pages.FirstOrDefault(p => p.ID == r.PageID);
            });


        }
    }
}
