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
            var persons = AppWrapper.Data.Persons.GetAll();

            listViewStatTotal.Items.Clear();
            foreach (var person in persons)
            {
                listViewStatTotal.Items.Add(new ListViewItem(new string[] {
                    person.Name,
                    stats.Where(s => s.PersonID == person.ID).Sum(s => s.Rank).ToString()
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
