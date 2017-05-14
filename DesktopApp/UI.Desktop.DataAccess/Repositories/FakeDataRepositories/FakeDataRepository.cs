using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UFart.Desktop.DataAccess.FakeData;
using UFart.Desktop.DataAccess.Repositories.Base;
using UFart.Desktop.DataAccess.Repositories.Base.Interfaces;
using UFart.Desktop.DataAccess.Repositories.WebApi;
using UFart.Desktop.Domain.Entity;
using UI.Desktop.DataAccess.Repositories.Base.Interfaces;

namespace UFart.Desktop.DataAccess.Repositories.FakeDataRepositories
{
    public class FakeDataRepository
        : AbstractRepository, IDataRepository
    {
        private FakeDataBase fakeContext;
        public FakeDataRepository()
            : base()
        {
            this.fakeContext = new FakeDataBase();
        }

        IEntityRepositoryBase<Keyword> keywordsRepository;
        public IEntityRepositoryBase<Keyword> Keywords
        {
            get
            {
                if (keywordsRepository == null)
                {
                    keywordsRepository = new FakeDataRepositoryBase<Keyword>(fakeContext.Keywords);
                }
                return keywordsRepository;
            }
        }

        IEntityRepositoryBase<Page> pagesRepository;
        public IEntityRepositoryBase<Page> Pages
        {
            get
            {
                if (pagesRepository == null)
                {
                    pagesRepository = new FakeDataRepositoryBase<Page>(fakeContext.Pages);
                }
                return pagesRepository;
            }
        }

        IPersonPageRankRepository personPagesRankRepository;
        public IPersonPageRankRepository PersonPagesRank
        {
            get
            {
                if (personPagesRankRepository == null)
                {
                    personPagesRankRepository = new FakePersonPageRankRepository(fakeContext.Ranks);
                }
                return personPagesRankRepository;
            }
        }

        IEntityRepositoryBase<Person> personsRepository;
        public IEntityRepositoryBase<Person> Persons
        {
            get
            {
                if (personsRepository == null)
                {
                    personsRepository = new FakeDataRepositoryBase<Person>(fakeContext.Persons);
                }
                return personsRepository;
            }
        }

        IEntityRepositoryBase<Site> sitesRepository;
        public IEntityRepositoryBase<Site> Sites
        {
            get
            {
                if (sitesRepository == null)
                {
                    sitesRepository = new FakeDataRepositoryBase<Site>(fakeContext.Sites);
                }
                return sitesRepository;
            }
        }
    }
}
