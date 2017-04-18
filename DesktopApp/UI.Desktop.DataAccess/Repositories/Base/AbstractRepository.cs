using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UFart.Desktop.DataAccess.Repositories.Base
{
    public abstract class AbstractRepository
    {

        public AbstractRepository()
        {
            
        }

        private bool disposed = false;
        public void Dispose()
        {
            if (!disposed)
            {
                
                GC.SuppressFinalize(this);
            }
            disposed = true;
        }
    }
}
