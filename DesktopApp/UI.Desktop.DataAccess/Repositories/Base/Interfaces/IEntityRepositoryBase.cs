using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Text;
using System.Threading.Tasks;

namespace UFart.Desktop.DataAccess.Repositories.Base.Interfaces
{
    public interface IEntityRepositoryBase<TEntity> where TEntity : class
    {
        TEntity Get(int id);
        IEnumerable<TEntity> GetAll();

        void Create(TEntity item);
        void Update(TEntity item);
        void Remove(TEntity item);
    }
}
