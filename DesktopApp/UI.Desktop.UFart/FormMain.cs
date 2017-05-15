using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace UFart.Desktop.UI
{
    public partial class FormMain : Form
    {
        private Brush tabPageTitleBrush = new SolidBrush(Color.Blue);

        public FormMain()
        {
            InitializeComponent();
        }

        private void tabControl1_DrawItem(object sender, DrawItemEventArgs e)
        {
            var g = e.Graphics;
            Font font;

            var tabPage = tabControlMainScreen.TabPages[e.Index];
            var tabBounds = tabControlMainScreen.GetTabRect(e.Index);

            if (e.State == DrawItemState.Selected)
            {
                font = new Font(e.Font, FontStyle.Bold);
            }
            else
            {
                font = new Font(e.Font, FontStyle.Regular);
            }

            var stringFlags = new StringFormat();
            stringFlags.Alignment = StringAlignment.Near;
            stringFlags.LineAlignment = StringAlignment.Center;
            g.DrawString(tabPage.Text, font, tabPageTitleBrush, tabBounds, new StringFormat(stringFlags));
        }
    }
}
