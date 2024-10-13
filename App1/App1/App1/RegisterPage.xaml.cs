using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace App1
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class RegisterPage : ContentPage
    {
        public RegisterPage()
        {
            InitializeComponent();
        }

            private async void OnRegisterButtonClicked(object sender, EventArgs e)
            {
                await OnRegisterClicked();
            }

            private async Task OnRegisterClicked()
            {
                var name = usernameEntry.Text; // Lấy giá trị từ Entry cho name
                var email = emailEntry.Text;
                var password = passwordEntry.Text;
                var confirmPassword = repeatPasswordEntry.Text;

                if (string.IsNullOrEmpty(name) || string.IsNullOrEmpty(email) ||
                    string.IsNullOrEmpty(password) || string.IsNullOrEmpty(confirmPassword))
                {
                    await DisplayAlert("Error", "All fields are required!", "OK");
                    return;
                }

                if (password != confirmPassword)
                {
                    await DisplayAlert("Error", "Passwords do not match!", "OK");
                    return;
                }

              

                var user = new User
                {
                    name = name,
                    email = email,
                    password = password
                };

                string apiUrl = "http://10.0.2.2:8081/auth/register"; // Địa chỉ API
                var json = JsonConvert.SerializeObject(user);
                var content = new StringContent(json, Encoding.UTF8, "application/json");

                try
                {
                    using (var client = new HttpClient())
                    {
                        var response = await client.PostAsync(apiUrl, content);
                        if (response.StatusCode == System.Net.HttpStatusCode.Created)
                        {
                            await DisplayAlert("Success", "Registered successfully!", "OK");
                            // Chuyển hướng đến trang đăng nhập sau khi đăng ký thành công
                      
                        }
                        else
                        {
                            await DisplayAlert("Error", "Registration failed!", "OK");
                        }
                    }
                }
                catch (Exception ex)
                {
                    await DisplayAlert("Error", $"An error occurred: {ex.Message}", "OK");
                }
            }

        
        private async void OnLoginLabelTapped(object sender, EventArgs e)
        {
            // Điều hướng người dùng đến trang đăng nhập
            await Navigation.PushAsync(new LoginPage());
        }

    }

    public class User
        {
            public string name { get; set; }
            public string email { get; set; }
            public string password { get; set; }
        }
}