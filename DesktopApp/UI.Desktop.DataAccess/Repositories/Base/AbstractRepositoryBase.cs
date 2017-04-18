using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UFart.Desktop.DataAccess.Repositories.Base
{
    public abstract class AbstractRepositoryBase<TEntity> where TEntity : class
    {
        internal List<TEntity> fakeData;
        public AbstractRepositoryBase()
        {

        }

        public AbstractRepositoryBase(List<TEntity> fakeData)
        {
            this.fakeData = fakeData;
        }




    }
}
