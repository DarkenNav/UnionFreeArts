using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UI.Desktop.DataAccess.Repositories.Base.Interfaces;

namespace UI.Desktop.DataAccess.Repositories.Base
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
