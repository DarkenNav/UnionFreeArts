using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Text;
using System.Threading.Tasks;

namespace UI.Desktop.DataAccess.Repositories.Base.Interfaces
{
    public interface IEntityRepositoryBase<TEntity> where TEntity : class
    {
        IEnumerable<TEntity> Get(
            Expression<Func<TEntity, bool>> filter = null,
            Func<IQueryable<TEntity>, IOrderedQueryable<TEntity>> order = null,
            int? skip = null,
            int? take = null
            );

        IEnumerable<TEntity> Find(Expression<Func<TEntity, bool>> func);

        void Create(TEntity item);
        void Update(TEntity item);
        void Remove(TEntity item);
    }
}
