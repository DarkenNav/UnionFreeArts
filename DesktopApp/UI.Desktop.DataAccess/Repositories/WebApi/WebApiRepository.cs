using UFart.Desktop.DataAccess.Repositories.Base;
using UFart.Desktop.DataAccess.Repositories.Base.Interfaces;
using UFart.Desktop.Domain.Entity;

namespace UFart.Desktop.DataAccess.Repositories.WebApi
{
    public class WebApiRepository
        : AbstractRepository, IDataRepository
    {
        private string url;
        public WebApiRepository(string url = "")
            : base()
        {
            if (url == "")
                this.url = @"http://localhost:8080";
            else
                this.url = url;
        }

        IEntityRepositoryBase<Keyword> keywordsRepository;
        public IEntityRepositoryBase<Keyword> Keywords
        {
            get
            {
                if (keywordsRepository == null)
                {
                    keywordsRepository = new WebApiRepositoryBase<Keyword>($"{url}/keyword/");
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
                    pagesRepository = new WebApiRepositoryBase<Page>($"{url}/page/");
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
                    personPagesRankRepository = new WebApiPersonPageRankRepository($"{url}/stat/");
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
                    personsRepository = new WebApiRepositoryBase<Person>($"{url}/person/");
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
                    sitesRepository = new WebApiRepositoryBase<Site>($"{url}/site/");
                }
                return sitesRepository;
            }
        }
    }
}
