namespace UFart.Desktop.UI.Interface
{
    partial class FormMain
    {
        /// <summary>
        /// Обязательная переменная конструктора.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Освободить все используемые ресурсы.
        /// </summary>
        /// <param name="disposing">истинно, если управляемый ресурс должен быть удален; иначе ложно.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Код, автоматически созданный конструктором форм Windows

        /// <summary>
        /// Требуемый метод для поддержки конструктора — не изменяйте 
        /// содержимое этого метода с помощью редактора кода.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(FormMain));
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.btnEveryDayStat = new System.Windows.Forms.Button();
            this.btnTotalStat = new System.Windows.Forms.Button();
            this.groupBox2 = new System.Windows.Forms.GroupBox();
            this.btnDisctinctPerson = new System.Windows.Forms.Button();
            this.btnDistinctSite = new System.Windows.Forms.Button();
            this.btnExit = new System.Windows.Forms.Button();
            this.groupBox1.SuspendLayout();
            this.groupBox2.SuspendLayout();
            this.SuspendLayout();
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.btnEveryDayStat);
            this.groupBox1.Controls.Add(this.btnTotalStat);
            this.groupBox1.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.groupBox1.Location = new System.Drawing.Point(12, 12);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(273, 156);
            this.groupBox1.TabIndex = 9;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "Статистика";
            // 
            // btnEveryDayStat
            // 
            this.btnEveryDayStat.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.btnEveryDayStat.ForeColor = System.Drawing.SystemColors.ControlText;
            this.btnEveryDayStat.Location = new System.Drawing.Point(17, 72);
            this.btnEveryDayStat.Name = "btnEveryDayStat";
            this.btnEveryDayStat.Size = new System.Drawing.Size(242, 29);
            this.btnEveryDayStat.TabIndex = 9;
            this.btnEveryDayStat.Text = "Ежедневная статистика";
            this.btnEveryDayStat.UseVisualStyleBackColor = true;
            this.btnEveryDayStat.Click += new System.EventHandler(this.btnEveryDayStat_Click);
            // 
            // btnTotalStat
            // 
            this.btnTotalStat.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.btnTotalStat.ForeColor = System.Drawing.SystemColors.ControlText;
            this.btnTotalStat.Location = new System.Drawing.Point(17, 37);
            this.btnTotalStat.Name = "btnTotalStat";
            this.btnTotalStat.Size = new System.Drawing.Size(242, 29);
            this.btnTotalStat.TabIndex = 8;
            this.btnTotalStat.Text = "Общая статистика";
            this.btnTotalStat.UseVisualStyleBackColor = true;
            this.btnTotalStat.Click += new System.EventHandler(this.btnTotalStat_Click);
            // 
            // groupBox2
            // 
            this.groupBox2.Controls.Add(this.btnDisctinctPerson);
            this.groupBox2.Controls.Add(this.btnDistinctSite);
            this.groupBox2.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.groupBox2.Location = new System.Drawing.Point(12, 174);
            this.groupBox2.Name = "groupBox2";
            this.groupBox2.Size = new System.Drawing.Size(273, 174);
            this.groupBox2.TabIndex = 10;
            this.groupBox2.TabStop = false;
            this.groupBox2.Text = "Справочники";
            // 
            // btnDisctinctPerson
            // 
            this.btnDisctinctPerson.Enabled = false;
            this.btnDisctinctPerson.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.btnDisctinctPerson.ForeColor = System.Drawing.SystemColors.ControlText;
            this.btnDisctinctPerson.Location = new System.Drawing.Point(6, 27);
            this.btnDisctinctPerson.Name = "btnDisctinctPerson";
            this.btnDisctinctPerson.Size = new System.Drawing.Size(253, 29);
            this.btnDisctinctPerson.TabIndex = 10;
            this.btnDisctinctPerson.Text = "Личности и ключевые слова";
            this.btnDisctinctPerson.UseVisualStyleBackColor = true;
            // 
            // btnDistinctSite
            // 
            this.btnDistinctSite.Enabled = false;
            this.btnDistinctSite.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.btnDistinctSite.ForeColor = System.Drawing.SystemColors.ControlText;
            this.btnDistinctSite.Location = new System.Drawing.Point(6, 62);
            this.btnDistinctSite.Name = "btnDistinctSite";
            this.btnDistinctSite.Size = new System.Drawing.Size(253, 29);
            this.btnDistinctSite.TabIndex = 9;
            this.btnDistinctSite.Text = "Сайты";
            this.btnDistinctSite.UseVisualStyleBackColor = true;
            // 
            // btnExit
            // 
            this.btnExit.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.btnExit.ForeColor = System.Drawing.SystemColors.ControlText;
            this.btnExit.Location = new System.Drawing.Point(152, 367);
            this.btnExit.Name = "btnExit";
            this.btnExit.Size = new System.Drawing.Size(133, 29);
            this.btnExit.TabIndex = 11;
            this.btnExit.Text = "Выход";
            this.btnExit.UseVisualStyleBackColor = true;
            this.btnExit.Click += new System.EventHandler(this.btnExit_Click);
            // 
            // FormMain
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(302, 429);
            this.Controls.Add(this.btnExit);
            this.Controls.Add(this.groupBox2);
            this.Controls.Add(this.groupBox1);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "FormMain";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Union Free Arts";
            this.groupBox1.ResumeLayout(false);
            this.groupBox2.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.Button btnEveryDayStat;
        private System.Windows.Forms.Button btnTotalStat;
        private System.Windows.Forms.GroupBox groupBox2;
        private System.Windows.Forms.Button btnDisctinctPerson;
        private System.Windows.Forms.Button btnDistinctSite;
        private System.Windows.Forms.Button btnExit;
    }
}

