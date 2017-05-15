using AutoMapper;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using UFart.Desktop.Domain.Entity;
using UFart.Desktop.UI.DTO;
using UI.Desktop.UFart.Implimentation;

namespace UI.Desktop.UFart.Interface
{
    public partial class FormTotal : Form
    {
        public FormTotal()
        {
            InitializeComponent();
            InitPageStatTotal();
        }

        private void InitPageStatTotal()
        {
            cbStatTotalSite.DataSource = Mapper.Map<IEnumerable<Site>, List<SiteDTO>>(AppWrapper.Data.Sites.GetAll());
            cbStatTotalSite.DisplayMember = "Name";

            UpdatelistViewStatTotal();
        }

        private void UpdatelistViewStatTotal()
        {
            var selectedSite = (SiteDTO)cbStatTotalSite.SelectedValue;

            var stats = AppWrapper.Data.PersonPagesRank.GetBySite(selectedSite.ID);

            listViewStatTotal.Items.Clear();
            foreach (var stat in stats)
            {
                listViewStatTotal.Items.Add(new ListViewItem(new string[] {
                    stat.PersonName,
                    stat.PersonRank.ToString()
                }));
            }

        }

        private void btnStatTotalApply_Click(object sender, EventArgs e)
        {
            UpdatelistViewStatTotal();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
