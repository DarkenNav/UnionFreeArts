namespace UI.Desktop.UFart.Interface
{
    partial class FormTotal
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(FormTotal));
            this.listViewStatTotal = new System.Windows.Forms.ListView();
            this.columnName = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.columnReferenceCount = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.btnStatTotalApply = new System.Windows.Forms.Button();
            this.cbStatTotalSite = new System.Windows.Forms.ComboBox();
            this.labelStatTotalSite = new System.Windows.Forms.Label();
            this.button1 = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // listViewStatTotal
            // 
            this.listViewStatTotal.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.columnName,
            this.columnReferenceCount});
            this.listViewStatTotal.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.listViewStatTotal.FullRowSelect = true;
            this.listViewStatTotal.GridLines = true;
            this.listViewStatTotal.HeaderStyle = System.Windows.Forms.ColumnHeaderStyle.Nonclickable;
            this.listViewStatTotal.Location = new System.Drawing.Point(10, 81);
            this.listViewStatTotal.MultiSelect = false;
            this.listViewStatTotal.Name = "listViewStatTotal";
            this.listViewStatTotal.Size = new System.Drawing.Size(562, 469);
            this.listViewStatTotal.TabIndex = 7;
            this.listViewStatTotal.TileSize = new System.Drawing.Size(100, 100);
            this.listViewStatTotal.UseCompatibleStateImageBehavior = false;
            this.listViewStatTotal.View = System.Windows.Forms.View.Details;
            // 
            // columnName
            // 
            this.columnName.Text = "Имя";
            this.columnName.Width = 200;
            // 
            // columnReferenceCount
            // 
            this.columnReferenceCount.Text = "Колличество упоминаний";
            this.columnReferenceCount.Width = 250;
            // 
            // btnStatTotalApply
            // 
            this.btnStatTotalApply.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.btnStatTotalApply.ForeColor = System.Drawing.SystemColors.ControlText;
            this.btnStatTotalApply.Location = new System.Drawing.Point(460, 11);
            this.btnStatTotalApply.Name = "btnStatTotalApply";
            this.btnStatTotalApply.Size = new System.Drawing.Size(112, 29);
            this.btnStatTotalApply.TabIndex = 6;
            this.btnStatTotalApply.Text = "Применить";
            this.btnStatTotalApply.UseVisualStyleBackColor = true;
            this.btnStatTotalApply.Click += new System.EventHandler(this.btnStatTotalApply_Click);
            // 
            // cbStatTotalSite
            // 
            this.cbStatTotalSite.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cbStatTotalSite.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.cbStatTotalSite.FormattingEnabled = true;
            this.cbStatTotalSite.Location = new System.Drawing.Point(68, 12);
            this.cbStatTotalSite.Name = "cbStatTotalSite";
            this.cbStatTotalSite.Size = new System.Drawing.Size(291, 28);
            this.cbStatTotalSite.TabIndex = 5;
            // 
            // labelStatTotalSite
            // 
            this.labelStatTotalSite.AutoSize = true;
            this.labelStatTotalSite.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.labelStatTotalSite.ForeColor = System.Drawing.SystemColors.ControlText;
            this.labelStatTotalSite.Location = new System.Drawing.Point(11, 15);
            this.labelStatTotalSite.Name = "labelStatTotalSite";
            this.labelStatTotalSite.Size = new System.Drawing.Size(51, 20);
            this.labelStatTotalSite.TabIndex = 4;
            this.labelStatTotalSite.Text = "Сайт:";
            // 
            // button1
            // 
            this.button1.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.button1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.button1.Location = new System.Drawing.Point(460, 46);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(112, 29);
            this.button1.TabIndex = 8;
            this.button1.Text = "Закрыть";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // FormTotal
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(584, 562);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.listViewStatTotal);
            this.Controls.Add(this.btnStatTotalApply);
            this.Controls.Add(this.cbStatTotalSite);
            this.Controls.Add(this.labelStatTotalSite);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "FormTotal";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Общая статистика";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ListView listViewStatTotal;
        private System.Windows.Forms.ColumnHeader columnName;
        private System.Windows.Forms.ColumnHeader columnReferenceCount;
        private System.Windows.Forms.Button btnStatTotalApply;
        private System.Windows.Forms.ComboBox cbStatTotalSite;
        private System.Windows.Forms.Label labelStatTotalSite;
        private System.Windows.Forms.Button button1;
    }
}