using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Text;
using System.Threading.Tasks;
using UI.Desktop.DataAccess.Repositories.Base.Interfaces;

namespace UI.Desktop.DataAccess.Repositories.Base
{
    public class EntityRepositoryBase<TEntity>
        : IEntityRepositoryBase<TEntity> where TEntity : class
    {
        public EntityRepositoryBase()
        {

        }

        public void Create(TEntity item)
        {
            throw new NotImplementedException();
        }

        public IEnumerable<TEntity> Find(Expression<Func<TEntity, bool>> func)
        {
            throw new NotImplementedException();
        }

        public IEnumerable<TEntity> Get(
            Expression<Func<TEntity, bool>> filter = null, 
            Func<IQueryable<TEntity>, IOrderedQueryable<TEntity>> order = null, 
            int? skip = default(int?), 
            int? take = default(int?))
        {
            throw new NotImplementedException();
        }

        public void Remove(TEntity item)
        {
            throw new NotImplementedException();
        }

        public void Update(TEntity item)
        {
            throw new NotImplementedException();
        }
    }
}
