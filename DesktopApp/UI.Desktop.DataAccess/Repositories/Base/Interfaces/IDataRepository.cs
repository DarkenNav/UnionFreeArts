using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UFart.Desktop.DataAccess.Repositories.Base.Interfaces;
using UFart.Desktop.Domain.Entity;

namespace UFart.Desktop.DataAccess.Repositories
{
    public interface IDataRepository
    {
        IEntityRepositoryBase<Person> Persons { get; }
        IEntityRepositoryBase<Keyword> Keywords { get; }
        IEntityRepositoryBase<Site> Sites { get; }
        IEntityRepositoryBase<Page> Pages { get; }
        IPersonPageRankRepository PersonPagesRank { get; }
    }
}
