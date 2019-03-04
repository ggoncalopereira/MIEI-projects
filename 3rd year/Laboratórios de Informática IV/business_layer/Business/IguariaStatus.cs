using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Business
{
    public enum IguariaStatus
    {
        /// <summary>
        /// Valor por defeito para marcação op. CRUD iguaria
        /// </summary>
        Default = 0,
        /// <summary>
        /// Valor de inserção para marcação op. CRUD iguaria
        /// </summary>
        ToInsert = 1,
        /// <summary>
        /// Valor de atualização para marcação op. CRUD iguaria
        /// </summary>
        ToUpdate = 2,
        /// <summary>
        /// Valor de remoção para marcação op. CRUD iguaria
        /// </summary>
        ToDelete = 3
    }
}