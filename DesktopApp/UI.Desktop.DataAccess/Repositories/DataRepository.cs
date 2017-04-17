using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UI.Desktop.DataAccess.Repositories.Base;
using UI.Desktop.DataAccess.Repositories.Base.Interfaces;
using UI.Desktop.UFart.Domain.Entity;

namespace UI.Desktop.DataAccess.Repositories
{
    public class DataRepository
        : AbstractRepository, IDataRepository
    {
        public DataRepository() 
            : base()
        {
        }

        IEntityRepositoryBase<Keyword> keywordsRepository;
        public IEntityRepositoryBase<Keyword> Keywords
        {
            get
            {
                if (keywordsRepository == null)
                    keywordsRepository = new EntityRepositoryBase<Keyword>();
                return keywordsRepository;
            }
        }

        IEntityRepositoryBase<Page> pagesRepository;
        public IEntityRepositoryBase<Page> Pages
        {
            get
            {
                if (pagesRepository == null)
                    pagesRepository = new EntityRepositoryBase<Page>();
                return pagesRepository;
            }
        }

        IEntityRepositoryBase<PersonPageRank> personPagesRankRepository;
        public IEntityRepositoryBase<PersonPageRank> PersonPagesRank
        {
            get
            {
                if (personPagesRankRepository == null)
                    personPagesRankRepository = new EntityRepositoryBase<PersonPageRank>();
                return personPagesRankRepository;
            }
        }

        IEntityRepositoryBase<Person> personsRepository;
        public IEntityRepositoryBase<Person> Persons
        {
            get
            {
                if (personsRepository == null)
                    personsRepository = new EntityRepositoryBase<Person>();
                return personsRepository;
            }
        }

        IEntityRepositoryBase<Site> sitesRepository;
        public IEntityRepositoryBase<Site> Sites
        {
            get
            {
                if (sitesRepository == null)
                    sitesRepository = new EntityRepositoryBase<Site>();
                return sitesRepository;
            }
        }
    }
}
