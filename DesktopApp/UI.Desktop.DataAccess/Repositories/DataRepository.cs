using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UFart.Desktop.DataAccess.FakeData;
using UFart.Desktop.DataAccess.Repositories.Base;
using UFart.Desktop.DataAccess.Repositories.Base.Interfaces;
using UFart.Desktop.Domain.Entity;

namespace UFart.Desktop.DataAccess.Repositories
{
    public class DataRepository
        : AbstractRepository, IDataRepository
    {
        IDataRepository dataRepository;
        public DataRepository(IDataRepository dataRepository) 
            : base()
        {
            this.dataRepository = dataRepository;
        }

        public IEntityRepositoryBase<Keyword> Keywords
        {
            get
            {
                return dataRepository.Keywords;
            }
        }


        public IEntityRepositoryBase<Page> Pages
        {
            get
            {
                return dataRepository.Pages;
            }
        }

        public IPersonPageRankRepository PersonPagesRank
        {
            get
            {
                return dataRepository.PersonPagesRank;
            }
        }

        public IEntityRepositoryBase<Person> Persons
        {
            get
            {
                return dataRepository.Persons;
            }
        }

        public IEntityRepositoryBase<Site> Sites
        {
            get
            {
                return dataRepository.Sites;
            }
        }
    }
}
