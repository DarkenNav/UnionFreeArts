using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UFart.Desktop.Domain.Entity;

namespace UFart.Desktop.DataAccess.FakeData
{
    public class FakePersonPagesRankList
        : List<PersonPageRank>
    {
        public FakePersonPagesRankList()
        {
            AddRange(new List<PersonPageRank> {
                new PersonPageRank { PersonID = 1, PageID = 101, Rank = 10 },
                new PersonPageRank { PersonID = 1, PageID = 102, Rank = 11 },
                new PersonPageRank { PersonID = 1, PageID = 103, Rank = 13 },
                new PersonPageRank { PersonID = 1, PageID = 104, Rank = 2 },

                new PersonPageRank { PersonID = 2, PageID = 101, Rank = 5 },
                new PersonPageRank { PersonID = 2, PageID = 102, Rank = 1 },
                new PersonPageRank { PersonID = 2, PageID = 103, Rank = 10 },
                new PersonPageRank { PersonID = 2, PageID = 104, Rank = 2 },

                new PersonPageRank { PersonID = 3, PageID = 101, Rank = 1 },
                new PersonPageRank { PersonID = 3, PageID = 102, Rank = 2 },
                new PersonPageRank { PersonID = 3, PageID = 103, Rank = 5 },
                new PersonPageRank { PersonID = 3, PageID = 104, Rank = 3 },

                new PersonPageRank { PersonID = 1, PageID = 1, Rank = 10 },
                new PersonPageRank { PersonID = 1, PageID = 2, Rank = 20 },
                new PersonPageRank { PersonID = 1, PageID = 3, Rank = 20 },
                new PersonPageRank { PersonID = 1, PageID = 4, Rank = 0 }
            });
        }
    }
}
