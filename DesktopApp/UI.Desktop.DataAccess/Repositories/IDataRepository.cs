using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UI.Desktop.DataAccess.Repositories.Base.Interfaces;
using UI.Desktop.UFart.Domain.Entity;

namespace UI.Desktop.DataAccess.Repositories
{
    public interface IDataRepository
    {
        IEntityRepositoryBase<Person> Persons { get; }
        IEntityRepositoryBase<Keyword> Keywords { get; }
        IEntityRepositoryBase<Site> Sites { get; }
        IEntityRepositoryBase<Page> Pages { get; }
        IEntityRepositoryBase<PersonPageRank> PersonPagesRank { get; }
    }
}
