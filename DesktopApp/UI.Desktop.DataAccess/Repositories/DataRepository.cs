using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UFart.Desktop.DataAccess.FakeData;
using UFart.Desktop.DataAccess.Repositories.Base;
using UFart.Desktop.DataAccess.Repositories.Base.Interfaces;
using UFart.Desktop.DataAccess.Repositories.Entities;
using UFart.Desktop.Domain.Entity;

namespace UFart.Desktop.DataAccess.Repositories
{
    public class DataRepository
        : AbstractRepository, IDataRepository
    {
        public DataRepository() 
            : base()
        {
        }

        private FakeDataBase fakeContext;
        public DataRepository(FakeDataBase fakeContext)
            : base()
        {
            this.fakeContext = fakeContext;
        }

        IEntityRepositoryBase<Keyword> keywordsRepository;
        public IEntityRepositoryBase<Keyword> Keywords
        {
            get
            {
                if (keywordsRepository == null)
                {
                    if (fakeContext != null)
                    {
                        keywordsRepository = new EntityRepositoryBase<Keyword>();
                    }
                    else
                    {
                        keywordsRepository = new EntityRepositoryBase<Keyword>();
                    }
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
                    if (fakeContext != null)
                    {
                        pagesRepository = new EntityRepositoryBase<Page>(fakeContext.Pages);
                    }
                    else
                    {
                        pagesRepository = new EntityRepositoryBase<Page>();
                    }
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
                    if (fakeContext != null)
                    {
                        personPagesRankRepository = new PersonPageRankRepository(fakeContext.Ranks);
                    }
                    else
                    {
                        personPagesRankRepository = new PersonPageRankRepository();
                    }
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
                    if (fakeContext != null)
                    {
                        personsRepository = new EntityRepositoryBase<Person>(fakeContext.Persons);
                    }
                    else
                    {
                        personsRepository = new EntityRepositoryBase<Person>();
                    }
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
                    if (fakeContext != null)
                    {
                        sitesRepository = new EntityRepositoryBase<Site>(fakeContext.Sites);
                    }
                    else
                    {
                        sitesRepository = new EntityRepositoryBase<Site>();
                    }
                }
                return sitesRepository;
            }
        }
    }
}
