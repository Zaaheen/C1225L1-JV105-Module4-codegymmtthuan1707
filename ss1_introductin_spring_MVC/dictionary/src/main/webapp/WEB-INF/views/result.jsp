<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Tailwind CSS CDN -->
<script src="https://cdn.tailwindcss.com"></script>

<!-- FontAwesome 6 Icons CDN -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

<!-- Google Fonts Inter -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700;800&display=swap" rel="stylesheet">

<style>
  /* CSS reset đảm bảo full màn hình không đè tràn nền trắng */
  html, body {
    width: 100vw;
    min-height: 100vh;
    margin: 0;
    padding: 0;
    background-color: #020617 !important; /* bg-slate-950 */
    color: #f8fafc;
    font-family: 'Inter', sans-serif;
    overflow-x: hidden;
  }
</style>


<!-- STREAMING_CHUNK:Rendering ambient background blurs... -->
<!-- Đèn chiếu sáng hiệu ứng Glassmorphism -->
<div class="fixed top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-[600px] h-[600px] bg-indigo-600/15 rounded-full blur-[120px] pointer-events-none"></div>
<div class="fixed top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-[400px] h-[400px] bg-sky-500/15 rounded-full blur-[100px] pointer-events-none"></div>

<!-- STREAMING_CHUNK:Building dictionary card container... -->
<!-- Khung chính căn giữa màn hình -->
<div class="relative w-full max-w-xl mx-auto bg-slate-900/90 backdrop-blur-2xl rounded-3xl border border-slate-800 shadow-2xl p-6 sm:p-8 space-y-6 z-10">

  <!-- STREAMING_CHUNK:Rendering header section... -->
  <!-- Header Tiêu đề -->
  <div class="text-center space-y-2">
    <div class="inline-flex items-center justify-center w-16 h-16 rounded-2xl bg-gradient-to-tr from-indigo-500 to-sky-400 p-0.5 shadow-lg shadow-indigo-500/20">
      <div class="w-full h-full bg-slate-950 rounded-[14px] flex items-center justify-center">
        <i class="fa-solid fa-book-bookmark text-2xl text-sky-400"></i>
      </div>
    </div>
    <h1 class="text-2xl sm:text-3xl font-extrabold bg-gradient-to-r from-white via-slate-200 to-slate-400 bg-clip-text text-transparent">
      Từ Điển Anh – Việt
    </h1>
    <p class="text-xs sm:text-sm text-slate-400">
      Tra cứu từ vựng Tiếng Anh sang Tiếng Việt nhanh chóng & chính xác
    </p>
  </div>

  <!-- STREAMING_CHUNK:Rendering search form input... -->
  <!-- Form nhập từ cần tra cứu -->
  <form action="${pageContext.request.contextPath}/lookup" method="post" class="space-y-4">
    <div class="space-y-1.5">
      <label for="keyword" class="block text-xs font-semibold text-slate-300 uppercase tracking-wider">
        Nhập từ Tiếng Anh cần tra
      </label>
      <div class="relative flex items-center">
        <div class="absolute inset-y-0 left-0 pl-3.5 flex items-center pointer-events-none text-slate-400">
          <i class="fa-solid fa-magnifying-glass text-slate-400"></i>
        </div>
        <input type="text" id="keyword" name="keyword"
               value="${keyword != null ? keyword : ''}" required
               placeholder="Ví dụ: hello, computer, developer..."
               autocomplete="off"
               class="w-full pl-10 pr-24 py-3.5 bg-slate-800/80 border border-slate-700/80 rounded-xl text-slate-100 placeholder-slate-500 focus:outline-none focus:ring-2 focus:ring-sky-500 focus:border-transparent transition duration-200 text-sm font-medium">
        <button type="submit"
                class="absolute right-1.5 bg-gradient-to-r from-indigo-500 to-sky-500 hover:from-indigo-400 hover:to-sky-400 text-white font-semibold py-2 px-4 rounded-lg shadow-md transition duration-200 flex items-center gap-1.5 text-xs cursor-pointer">
          <i class="fa-solid fa-search"></i>
          <span>Tra cứu</span>
        </button>
      </div>
    </div>
  </form>

  <!-- STREAMING_CHUNK:Rendering lookup result section... -->
  <!-- Hiển thị kết quả sau khi tra cứu -->
  <c:if test="${not empty keyword}">
    <c:choose>
      <%-- Trạng thái TÌM THẤY TỪ --%>
      <c:when test="${found}">
        <div class="bg-gradient-to-br from-emerald-950/80 to-slate-900 border border-emerald-500/30 rounded-2xl p-5 text-white shadow-xl relative overflow-hidden group">
          <div class="flex items-center justify-between mb-3">
                        <span class="text-emerald-400 text-xs font-semibold tracking-wider uppercase bg-emerald-500/10 px-3 py-1 rounded-full border border-emerald-500/20 flex items-center gap-1.5">
                            <i class="fa-solid fa-circle-check text-emerald-400"></i> Tìm thấy từ vựng
                        </span>
            <span class="text-slate-400 text-xs flex items-center gap-1">
                            <i class="fa-solid fa-language text-sky-400"></i> Anh - Việt
                        </span>
          </div>

          <div class="space-y-3">
            <div>
              <span class="text-xs text-slate-400 font-medium">Từ tiếng Anh:</span>
              <div class="text-2xl font-black text-indigo-300 tracking-wide capitalize flex items-center gap-2">
                <span>${keyword}</span>
                <span class="text-xs font-normal text-slate-400 bg-slate-800 px-2 py-0.5 rounded-md border border-slate-700">/English/</span>
              </div>
            </div>

            <div class="pt-2 border-t border-slate-800">
              <span class="text-xs text-slate-400 font-medium">Nghĩa tiếng Việt:</span>
              <div class="text-xl font-bold text-emerald-300 mt-0.5">
                  ${meaning}
              </div>
            </div>
          </div>
        </div>
      </c:when>

      <%-- Trạng thái KHÔNG TÌM THẤY TỪ --%>
      <c:otherwise>
        <div class="bg-gradient-to-br from-rose-950/70 to-slate-900 border border-rose-500/30 rounded-2xl p-5 text-white shadow-xl relative overflow-hidden">
          <div class="flex items-center gap-3">
            <div class="w-10 h-10 rounded-xl bg-rose-500/20 border border-rose-500/30 flex items-center justify-center shrink-0">
              <i class="fa-solid fa-circle-xmark text-xl text-rose-400"></i>
            </div>
            <div>
              <h3 class="text-sm font-bold text-rose-300">Không tìm thấy kết quả</h3>
              <p class="text-xs text-slate-300 mt-0.5">${message}</p>
            </div>
          </div>
        </div>
      </c:otherwise>
    </c:choose>
  </c:if>

  <!-- STREAMING_CHUNK:Rendering suggestion tags section... -->
  <!-- Danh sách từ vựng gợi ý có sẵn trong kho -->
  <div class="space-y-2 pt-2 border-t border-slate-800">
    <div class="flex items-center justify-between">
            <span class="text-xs font-semibold text-slate-400 uppercase tracking-wider flex items-center gap-1.5">
                <i class="fa-solid fa-lightbulb text-amber-400"></i> Từ vựng có sẵn trong từ điển
            </span>
      <span class="text-[11px] text-slate-500">Bấm để tra ngay</span>
    </div>

    <div class="flex flex-wrap gap-1.5 max-h-32 overflow-y-auto pr-1">
      <c:forEach var="word" items="${dictionaryKeys}">
        <a href="${pageContext.request.contextPath}/lookup?keyword=${word}"
           class="text-xs px-2.5 py-1 rounded-lg bg-slate-800 hover:bg-indigo-600/30 hover:border-indigo-500/50 text-slate-300 hover:text-indigo-200 border border-slate-700/60 transition duration-150">
            ${word}
        </a>
      </c:forEach>
    </div>
  </div>

  <!-- STREAMING_CHUNK:Rendering footer info... -->
  <div class="text-center pt-2 border-t border-slate-800">
    <p class="text-[11px] text-slate-500">
      Ứng dụng Spring MVC Từ Điển Anh - Việt &bull; Tra cứu theo danh mục dữ liệu
    </p>
  </div>

</div>
