using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UI.Desktop.UFart.UI.DTO;

namespace UI.Desktop.UFart.FakeData
{
    public class FakePersonPagesRankList
        : List<PersonPageRankDTO>
    {
        public FakePersonPagesRankList()
        {
            AddRange(new List<PersonPageRankDTO> {
                new PersonPageRankDTO { PersonID = 1, PageID = 101, Rank = 10 },
                new PersonPageRankDTO { PersonID = 1, PageID = 102, Rank = 11 },
                new PersonPageRankDTO { PersonID = 1, PageID = 103, Rank = 13 },
                new PersonPageRankDTO { PersonID = 1, PageID = 104, Rank = 2 },

                new PersonPageRankDTO { PersonID = 2, PageID = 101, Rank = 5 },
                new PersonPageRankDTO { PersonID = 2, PageID = 102, Rank = 1 },
                new PersonPageRankDTO { PersonID = 2, PageID = 103, Rank = 10 },
                new PersonPageRankDTO { PersonID = 2, PageID = 104, Rank = 2 },

                new PersonPageRankDTO { PersonID = 3, PageID = 101, Rank = 1 },
                new PersonPageRankDTO { PersonID = 3, PageID = 102, Rank = 2 },
                new PersonPageRankDTO { PersonID = 3, PageID = 103, Rank = 5 },
                new PersonPageRankDTO { PersonID = 3, PageID = 104, Rank = 3 },

                new PersonPageRankDTO { PersonID = 1, PageID = 1, Rank = 10 },
                new PersonPageRankDTO { PersonID = 1, PageID = 2, Rank = 20 },
                new PersonPageRankDTO { PersonID = 1, PageID = 3, Rank = 20 },
                new PersonPageRankDTO { PersonID = 1, PageID = 4, Rank = 0 }
            });
        }
    }
}
