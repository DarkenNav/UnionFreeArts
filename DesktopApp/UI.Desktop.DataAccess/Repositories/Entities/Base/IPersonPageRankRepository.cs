using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Text;
using System.Threading.Tasks;
using UFart.Desktop.DataAccess.Repositories.Base.Interfaces;
using UFart.Desktop.Domain.Entity;

namespace UFart.Desktop.DataAccess.Repositories.Base.Interfaces
{
    public interface IPersonPageRankRepository
        : IEntityRepositoryBase<PersonPageRank>
    {
        IEnumerable<PersonPageRank> GetBySite(int idSite);
    }
}
