using Newtonsoft.Json;
using System;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Threading.Tasks;
using System.Xml;
using Xamarin.Forms;

namespace App1
{
    public partial class HomePage : ContentPage
    {
        private string _token;

        public HomePage(string token)
        {
            InitializeComponent();
            _token = token;
            LoadUserInfo();
        }

        private async void LoadUserInfo()
        {
            string apiUrl = "http://10.0.2.2:8081/user/current-user"; // Địa chỉ API để lấy thông tin người dùng

            try
            {
                using (var client = new HttpClient())
                {
                    client.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Bearer", _token);
                    var response = await client.GetAsync(apiUrl);
                    if (response.IsSuccessStatusCode)
                    {
                        var responseData = await response.Content.ReadAsStringAsync();
                        var userInfo = JsonConvert.DeserializeObject<GetUserInfoDto>(responseData);

                        // Hiển thị thông tin người dùng
                        nameLabel.Text = $"Name: {userInfo.Name}";
                        emailLabel.Text = $"Email: {userInfo.Email}";
                        balanceLabel.Text = $"Balance: {userInfo.Balance:C}"; // Định dạng tiền tệ
                        cardNumberLabel.Text = $"Card Number: {userInfo.CardNumber}";
                        bankLabel.Text = $"Bank: {userInfo.Bank}";
                        createdLabel.Text = $"Created At: {userInfo.Created}";
                        updatedLabel.Text = $"Updated At: {userInfo.Updated}";
                    }
                    else
                    {
                        await DisplayAlert("Error", "Failed to load user information.", "OK");
                    }
                }
            }
            catch (Exception ex)
            {
                await DisplayAlert("Error", $"An error occurred: {ex.Message}", "OK");
            }
        }
    }

    public class GetUserInfoDto
    {
        public string Name { get; set; }
        public string Email { get; set; }
        public double Balance { get; set; }
        public string CardNumber { get; set; }
        public string Bank { get; set; }
        public DateTime Created { get; set; }
        public DateTime Updated { get; set; }
    }
}
