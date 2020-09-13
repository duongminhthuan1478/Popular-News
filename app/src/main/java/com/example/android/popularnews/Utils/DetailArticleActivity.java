package com.example.android.popularnews.Utils;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.android.popularnews.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class DetailArticleActivity extends AppCompatActivity {

    private WebView webView;
    private String urlDetail;
    private DetailContentAsynTask detailAsyncTask;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity_article);

        Intent intent = getIntent();
        urlDetail = intent.getStringExtra("urlDetail");

        webView = findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setDomStorageEnabled(true);
        //webView.getSettings().setJavaScriptEnabled(true);



//        webView.setWebViewClient(new WebViewClient() {
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
//                // Inject CSS when page is done loading
//                final Handler handler = new Handler();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        // Do something after 5s = 5000ms
//                        Toast.makeText(getApplicationContext(), "ABC", Toast.LENGTH_LONG).show();
//                        webView.loadUrl("javascript:document.querySelectorAll('header a img')[0].style.display = 'none';");
//                    }
//                }, 2000);
//
//            }
//        });
        webView.loadUrl(urlDetail);

        detailAsyncTask = new DetailContentAsynTask();
        detailAsyncTask.execute();
    }

    private class DetailContentAsynTask extends AsyncTask<Void, Void, Void> {
        Element img;
        Document document;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                //Connect to the website
                document = Jsoup.connect(urlDetail).get();

                //img = document.select(".sidebar-1").first();
                Log.d("ABC", ""+img);

                // Download image from URL
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            String a = "<h2><p>Sáng nay (13/9), Thủ tướng Chính phủ Nguyễn Xuân Phúc đã tới dự và phát biểu chỉ đạo tại lễ kỷ niệm 75 năm ngày truyền thống Thông tấn xã Việt Nam (TTXVN) và đón nhận Huân chương Lao động hạng Nhất.</p></h2><p>Dự lễ kỷ niệm có ủy viên Bộ Chính trị, Bí thư Trung ương Đảng, Trưởng Ban Tuyên giáo Trung ương Võ Văn Thưởng; Bí thư Trung ương Đảng, Trưởng ban Nội chính Trung ương Phan Đình Trạc; Bí thư Trung ương Đảng, Chánh án TANDTC Nguyễn Hòa Bình cùng đại diện các bộ, ban ngành, lãnh đạo, cán bộ, phóng viên TTXVN qua các thời kỳ.</p> <table class=\"FmsArticleBoxStyle ImageBox ImageCenterBox Border-1 image\"> <tbody> <tr> <td class=\"FmsArticleBoxStyle-Images image \"><img src=\"https://vnn-imgs-f.vgcloud.vn/2020/09/13/12/119107696-3458410174198137-2738184115052442424-o.jpg\" alt=\"{keywords}\" /></td> </tr> <tr> <td class=\"FmsArticleBoxStyle-Content image_desc\">Thủ tướng tham quan triển lãm và phòng trưng bày TTXVN</td> </tr> </tbody> </table> <p>Phát biểu tại lễ kỷ niệm, Thủ tướng bày tỏ xúc động khi tham quan phòng truyền thống TTXVN -  một không gian treo kín những tấm chân dung các nhà báo liệt sĩ. Lịch sử 75 năm của TTXVN được viết nên bởi những sự hy sinh thầm lặng, để “dòng thông tin chính thống không bao giờ ngừng chảy”.</p> <p>Thủ tướng điểm lại quá trình ra đời và phát triển TTXVN, vượt qua biết bao khó khăn, gian khổ, hy sinh, chuyển phát thông tin, hình ảnh một cách nhanh chóng, khách quan, sôi động về cuộc đấu tranh anh dũng của quân và dân ta.</p> <p>Những thông tin nóng hổi từ các mặt trận đã góp phần khơi dậy ý chí quyết tâm chiến đấu, chiến thắng quân xâm lược, giành độc lập dân tộc, thống nhất đất nước, tạo động lực cho các phong trào thi đua sôi nổi ở cả tiền tuyến và hậu phương, thực hiện lời Bác Hồ căn dặn: “Tin tức càng nhanh, kháng chiến càng mau thắng lợi”.</p> <table class=\"FmsArticleBoxStyle ImageBox ImageCenterBox Border-1 image\"> <tbody> <tr> <td class=\"FmsArticleBoxStyle-Images image\"><img src=\"https://vnn-imgs-f.vgcloud.vn/2020/09/13/12/119204952-3458502684188886-6207295671016478924-o.jpg\" alt=\"{keywords}\" /></td> </tr> <tr> <td class=\"FmsArticleBoxStyle-Content image_desc\">Thủ tướng với các cán bộ lão thành TTXVN</td> </tr> </tbody> </table> <p>Thủ tướng cho rằng: \"Nhiều bạn phóng viên đang ngồi đây sinh ra và lớn lên trong hòa bình, không hề biết đến chiến tranh. Nhưng những trang sử hào hùng, sự hy sinh anh dũng của những nhà báo thông tấn thế hệ đi trước vẫn còn lưu lại ở nơi đây...</p> <p>Những thế hệ tiếp nối, phải viết tiếp lịch sử phát triển của ngành sao cho xứng đáng với các thế hệ đi trước, với đồng nghiệp, xứng đáng với những người đã viết nên một “biên niên sử” báo chí về dân tộc Việt Nam\".</p> <p>Từ số lượng cán bộ ít ỏi thuở ban đầu, TTXVN đã trở thành cơ quan truyền thông đa phương tiện chủ lực, cơ quan báo chí đối ngoại chủ lực quốc gia. Trao đổi thông tin trực tuyến với trên 40 hãng thông tấn và tổ chức báo chí quốc tế.</p> <p>Gần 2.500 cán bộ, phóng viên, biên tập viên, kỹ thuật viên, trải rộng khắp 63 tỉnh, thành và 30 địa bàn quan trọng trên thế giới, trở thành cơ quan báo chí có mạng lưới thông tin rộng nhất, phong phú nhất so với các cơ quan báo chí trong nước. </p> <p>Thủ tướng cho biết khi đại dịch Covid-19 xảy ra, tác động chưa từng có đến mọi mặt đời sống kinh tế, xã hội của đất nước, TTXVN một lần nữa thể hiện vai trò tiên phong trong công tác thông tin, với những bản tin kịp thời, làm tốt vai trò định hướng dư luận.</p> <table class=\"FmsArticleBoxStyle ImageBox ImageCenterBox Border-1 image\"> <tbody> <tr> <td class=\"FmsArticleBoxStyle-Images image \"><img src=\"https://vnn-imgs-f.vgcloud.vn/2020/09/13/12/thu-tuong-viet-tiep-bien-nien-su-bao-chi-ve-dan-toc-viet-nam.jpg\" alt=\"{keywords}\" /></td> </tr> <tr> <td class=\"FmsArticleBoxStyle-Content image_desc\"/> </tr> </tbody> </table> <p>Thể hiện sự chỉ đạo đồng bộ, quyết liệt, kịp thời của Đảng và Nhà nước ta, tạo sự đồng thuận xã hội, phát huy lòng yêu nước, sức mạnh tổng hợp của toàn dân, đồng thời, giúp Chính phủ, Thủ tướng trong công tác chỉ đạo, điều hành kinh tế - xã hội của đất nước với mục tiêu kép hiện nay.</p> <p>Thủ tướng ghi nhận và biểu dương những nỗ lực, thành tích của các thế hệ lãnh đạo, cán bộ, phóng viên, kỹ thuật viên, nhân viên của TTXVN qua các thời kỳ đã đóng góp to lớn cho công cuộc xây dựng và bảo vệ Tổ quốc.</p> <p><strong>Cần làm tốt hơn nữa vai trò \"ngân hàng tin\" quốc gia</strong></p> <p>Người đứng đầu Chính phủ nhận định thời gian tới đứng trước thời cơ, khó khăn, thách thức đòi hỏi sự quyết tâm cao của cả hệ thống chính trị, của toàn Đảng, toàn quân và toàn dân ta, trong đó có vai trò to lớn của báo chí, truyền thông, của TTXVN.</p> <p>Ông đề nghị TTXVN cần tiếp tục tiên phong trong công tác thông tin, tuyên truyền. Thông tin nhanh chóng, chính xác và sinh động về chủ trương, đường lối của Đảng, chính sách, pháp luật của Nhà nước.</p> <table class=\"FmsArticleBoxStyle ImageBox ImageCenterBox Border-1 image\"> <tbody> <tr> <td class=\"FmsArticleBoxStyle-Images image\"><img src=\"https://vnn-imgs-f.vgcloud.vn/2020/09/13/12/119217545-3458525680853253-3332572842219848084-o.jpg\" alt=\"{keywords}\" /></td> </tr> <tr> <td class=\"FmsArticleBoxStyle-Content image_desc\">Thủ tướng trao Huân chương Lao động hạng Nhất cho TTXVN</td> </tr> </tbody> </table> <table class=\"FmsArticleBoxStyle ImageBox ImageCenterBox Border-1 image\"> <tbody> <tr> <td class=\"FmsArticleBoxStyle-Images image \"><img src=\"https://vnn-imgs-f.vgcloud.vn/2020/09/13/12/119114343-3458522074186947-9000413982801473266-o.jpg\" alt=\"{keywords}\" /></td> </tr> <tr> <td class=\"FmsArticleBoxStyle-Content image_desc\"/> </tr> </tbody> </table> <p>Phản ánh kịp thời tâm tư, nguyện vọng của nhân dân, kịp thời đấu tranh với những biểu hiện tiêu cực, những quan điểm sai trái của các thế lực thù địch; bảo vệ vững chắc nền tảng tư tưởng của Đảng, bảo vệ đường lối, chính sách của Đảng và Nhà nước, bảo vệ chủ quyền lãnh thổ, giữ vững ổn định chính trị - xã hội, nêu cao tinh thần đại đoàn kết dân tộc.</p> <p>Phấn đấu để có ngày càng nhiều hơn những tác phẩm báo chí mang đậm hơi thở cuộc sống, những bài chính luận, bình luận sắc sảo, có sức lan tỏa mạnh mẽ và rộng khắp, góp phần định hướng dư luận xã hội; củng cố niềm tin của nhân dân vào sự lãnh đạo của Đảng và Nhà nước.</p> <p>Theo Thủ tướng nhiệm vụ trước mắt và hết sức quan trọng của TTXVN là đẩy mạnh thông tin, tuyên truyền về Đại hội Đảng các cấp, tiến tới Đại hội đại biểu toàn quốc lần thứ XIII của Đảng, phản ánh ý kiến góp ý của các tầng lớp nhân dân đối với dự thảo các văn kiện và báo cáo trình Đại hội Đảng.</p> <p>Là cơ quan thông tấn đa phương tiện chủ lực quốc gia, TTXVN có con đường riêng của mình là “ngân hàng tin” cung cấp thông tin định hướng chính thống cho hệ thống báo chí, cho công chúng trong và ngoài nước, là nguồn thông tin báo cáo có giá trị.</p> <p>\"Đó là chính là bản sắc riêng, giá trị nền tảng mà TTXVN cần tiếp tục phát huy, giữ vững trong quá trình xây dựng các định hướng phát triển tương lai của ngành. Không phải ngẫu nhiên, cả nước hiện có hơn 800 cơ quan báo chí, trong đó có nhiều báo in, báo điện tử, nhiều đài truyền hình, nhưng chỉ có 1 cơ quan thông tấn\", Thủ tướng khẳng định.</p> <table class=\"FmsArticleBoxStyle ImageBox ImageCenterBox Border-1 image\"> <tbody> <tr> <td class=\"FmsArticleBoxStyle-Images image \"><img src=\"https://vnn-imgs-f.vgcloud.vn/2020/09/13/12/119057298-3458548197517668-4301295920091101740-o.jpg\" alt=\"{keywords}\" /></td> </tr> <tr> <td class=\"FmsArticleBoxStyle-Content image_desc\">Thủ tướng phát biểu tại lễ kỷ niệm</td> </tr> </tbody> </table> <p>Thủ tướng đề nghị mỗi người làm báo của TTXVN cần nhận thức rõ trách nhiệm, tâm huyết, không ngừng nâng cao bản lĩnh chính trị, nâng cao chuyên môn, mở rộng tầm hiểu biết, nhạy bén, sẵn sàng dấn thân và làm chủ khoa học, công nghệ tiên tiến.</p> <p>TTXVN cần tiếp tục đổi mới mạnh mẽ về công nghệ, phát triển trở thành cơ quan truyền thông đa phương tiện, hiện đại, đi đầu trong chuyển đổi số, ứng dụng công nghệ mới, trí tuệ nhân tạo.</p> <p>Kết hợp tốt giữa nội dung và kỹ thuật công nghệ, làm tốt hơn nữa vai trò là “ngân hàng tin” của quốc gia, tiếp tục giữ vững vị thế là một trung tâm thông tin tin cậy của Đảng và nhà nước, một hãng thông tấn có uy tín trong khu vực.</p> <p>Với 75 năm truyền thống anh hùng, truyền thống tiên phong trên tuyến đầu thông tin, giữ mạch nguồn thông tin luôn là dòng chảy chính thống, Thủ tướng tưởng: \"Từ cái gốc, cái nền tảng ấy, TTXVN sẽ tiếp tục phát triển mạnh mẽ, vươn xa\".</p> <p><strong>Thành Nam - </strong><em>Ảnh:</em><strong> TTXVN</strong></p> <div class=\"inner-article\"><a href=\"//vietnamnet.vn/vn/thoi-su/chinh-tri/bo-chinh-tri-cho-y-kien-phuong-an-nhan-su-cap-uy-cua-quan-doi-va-5-tinh-673445.html#inner-article\"><img class=\"thumb2 left m-r-10\" src=\"https://vnn-imgs-f.vgcloud.vn/2020/09/13/09/bo-chinh-tri-gop-y-phuong-an-nhan-su-nhan-su-cap-uy-cua-quan-doi-va-5-tinh.jpg?w=145&amp;h=101\" alt=\"Bộ Chính trị cho ý kiến phương án nhân sự cấp ủy của Quân đội và 5 tỉnh\" width=\"145\" height=\"101\" /></a> <h4 class=\"title f-14 c-000\"><a href=\"//vietnamnet.vn/vn/thoi-su/chinh-tri/bo-chinh-tri-cho-y-kien-phuong-an-nhan-su-cap-uy-cua-quan-doi-va-5-tinh-673445.html#inner-article\">Bộ Chính trị cho ý kiến phương án nhân sự cấp ủy của Quân đội và 5 tỉnh</a></h4> <p class=\"summary d-table c-000\">Bộ Chính trị làm việc tập thể và tiếp tục làm việc theo nhóm với 6 Đảng bộ trực thuộc Trung ương để cho ý kiến vào dự thảo các văn kiện và phương án nhân sự cấp ủy nhiệm kỳ 2020 - 2025.</p> </div>";
            webView.loadDataWithBaseURL(null,"<style> td{font-size: 40px !important; font-family: 'Montserrat', sans-serif;} body{font-size: 50px !important; font-family: 'Montserrat', sans-serif;} img{max-width:100%} </style>"
                    +a,"text/html","utf-8",null);
//            imageView.setImageBitmap(bitmap);
//            textView.setText(title);
//            progressDialog.dismiss();
        }
    }
}