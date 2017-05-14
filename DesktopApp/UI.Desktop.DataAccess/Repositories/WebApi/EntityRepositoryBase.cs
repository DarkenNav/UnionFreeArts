using AutoMapper;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Text;
using System.Threading.Tasks;
using UFart.Desktop.DataAccess.FakeData;
using UFart.Desktop.DataAccess.Repositories.Base.Interfaces;
using UFart.Desktop.Domain.Entity.Base;

namespace UFart.Desktop.DataAccess.Repositories.WebApi
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

        public TEntity Get(int id)
        {
            throw new NotImplementedException();

        }

        public IEnumerable<TEntity> GetAll()
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
