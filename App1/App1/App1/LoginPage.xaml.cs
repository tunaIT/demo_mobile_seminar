using Newtonsoft.Json;
using System;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace App1
{
    public partial class LoginPage : ContentPage
    {
        public LoginPage()
        {
            InitializeComponent();
        }

        private async void OnLoginButtonClicked(object sender, EventArgs e)
        {
            await OnLoginClicked();
        }

        private async Task OnLoginClicked()
        {
            var email = emailLogin.Text; // Lấy giá trị từ Entry cho email
            var password = passwordLogin.Text; // Lấy giá trị từ Entry cho password

            // Kiểm tra thông tin đăng nhập
            if (string.IsNullOrWhiteSpace(email) || string.IsNullOrWhiteSpace(password))
            {
                await DisplayAlert("Error", "Email and password cannot be empty!", "OK");
                return;
            }

            var user = new UserLogin
            {
                email = email,
                password = password
            };

            string apiUrl = "http://10.0.2.2:8081/auth/login"; // Địa chỉ API cho đăng nhập
            var json = JsonConvert.SerializeObject(user);
            var content = new StringContent(json, Encoding.UTF8, "application/json");

            try
            {
                using (var client = new HttpClient())
                {
                    var response = await client.PostAsync(apiUrl, content);
                    if (response.IsSuccessStatusCode)
                    {
                        var responseData = await response.Content.ReadAsStringAsync();
                        var loginResponse = JsonConvert.DeserializeObject<LoginResponse>(responseData);

                        // Lưu token để sử dụng sau này
                        string token = loginResponse.Token;

                        await DisplayAlert("Success", "Logged in successfully!", "OK");

                        // Điều hướng đến trang HomePage và truyền token
                        await Navigation.PushAsync(new HomePage(token));
                    }
                    else
                    {
                        await DisplayAlert("Error", "Login failed! Check your credentials.", "OK");
                    }
                }
            }
            catch (Exception ex)
            {
                await DisplayAlert("Error", $"An error occurred: {ex.Message}", "OK");
            }
        }
    }

    public class UserLogin
    {
        public string email { get; set; }
        public string password { get; set; }
    }

    public class LoginResponse
    {
        public string Token { get; set; } // Lưu trữ token nhận được từ backend
    }
}
