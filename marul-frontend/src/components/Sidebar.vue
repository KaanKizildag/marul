<template>
  <aside class="sidenav navbar navbar-vertical navbar-expand-xs border-0 border-radius-xl my-3 fixed-start ms-3   bg-gradient-dark" id="sidenav-main">
    <div class="sidenav-header">
      <i class="fas fa-times p-3 cursor-pointer text-white opacity-5 position-absolute end-0 top-0 d-none d-xl-none" aria-hidden="true" id="iconSidenav"></i>
      <a class="navbar-brand m-0" href=" https://demos.creative-tim.com/material-dashboard/pages/dashboard " target="_blank">
        <img src="../../public/assets/img/logo-ct.png" class="navbar-brand-img h-100" alt="main_logo">
        <span class="ms-1 font-weight-bold text-white">MARUL</span>
      </a>
    </div>
    <hr class="horizontal light mt-0 mb-2">
    <div class="collapse navbar-collapse  w-auto " id="sidenav-collapse-main">
      <ul class="navbar-nav">
        <li class="nav-item">
          <router-link to="/">
          <a class="nav-link text-white " :class="props.activePage === 'ANASAYFA' ? 'active bg-gradient-primary' : ''" href="../pages/dashboard.html">
            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
              <i class="material-icons opacity-10">dashboard</i>
            </div>
            <span class="nav-link-text ms-1">ANASAYFA</span>
          </a>
          </router-link>
        </li>

        <li class="nav-item">
          <a class="nav-link text-white " :class="props.activePage === 'SATIŞ' ? 'active bg-gradient-primary' : ''" href="../pages/tables.html">
            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
              <i class="material-icons opacity-10">table_view</i>
            </div>
            <span class="nav-link-text ms-1">SATIŞ</span>
          </a>
        </li>
        <li class="nav-item">
          <router-link to="/product">
          <a class="nav-link text-white " :class="props.activePage === 'ÜRÜN' ? 'active bg-gradient-primary' : ''" href="../pages/billing.html">
            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
              <i class="material-icons opacity-10">receipt_long</i>
            </div>
            <span class="nav-link-text ms-1">ÜRÜN</span>
          </a>
          </router-link>
        </li>
        <li class="nav-item">
          <a class="nav-link text-white " :class="props.activePage === 'STOK' ? 'active bg-gradient-primary' : ''" href="../pages/billing.html">
            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
              <i class="material-icons opacity-10">receipt_long</i>
            </div>
            <span class="nav-link-text ms-1">STOK</span>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link text-white " :class="props.activePage === 'MÜŞTERİ' ? 'active bg-gradient-primary' : ''" href="../pages/virtual-reality.html">
            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
              <i class="material-icons opacity-10">view_in_ar</i>
            </div>
            <span class="nav-link-text ms-1">MÜŞTERİ</span>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link text-white " :class="props.activePage === 'RAPOR' ? 'active bg-gradient-primary' : ''" href="../pages/rtl.html">
            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
              <i class="material-icons opacity-10">format_textdirection_r_to_l</i>
            </div>
            <span class="nav-link-text ms-1">RAPOR</span>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link text-white " :class="props.activePage === 'E POSTA' ? 'active bg-gradient-primary' : ''" href="../pages/notifications.html">
            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
              <i class="material-icons opacity-10">notifications</i>
            </div>
            <span class="nav-link-text ms-1">E POSTA</span>
          </a>
        </li>
        <li class="nav-item mt-3">
          <h6 class="ps-4 ms-2 text-uppercase text-xs text-white font-weight-bolder opacity-8">HESAP</h6>
        </li>
        <li class="nav-item">
          <a class="nav-link text-white " :class="props.activePage === 'PROFİL' ? 'active bg-gradient-primary' : ''" href="../pages/profile.html">
            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
              <i class="material-icons opacity-10">person</i>
            </div>
            <span class="nav-link-text ms-1">PROFİL</span>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link text-white " :class="props.activePage === 'GİRİŞ YAP' ? 'active bg-gradient-primary' : ''" href="../pages/sign-in.html">
            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
              <i class="material-icons opacity-10">login</i>
            </div>
            <span class="nav-link-text ms-1">GİRİŞ YAP</span>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link text-white " :class="props.activePage === 'ÜYE OL' ? 'active bg-gradient-primary' : ''" href="../pages/sign-up.html">
            <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
              <i class="material-icons opacity-10">assignment</i>
            </div>
            <span class="nav-link-text ms-1">ÜYE OL</span>
          </a>
        </li>
      </ul>
    </div>
    <div class="sidenav-footer position-absolute w-100 bottom-0 ">
      <div class="mx-3">
        <a class="btn bg-gradient-primary mt-4 w-100"  type="button">??????????</a>
      </div>
    </div>
  </aside>
</template>

<script setup>
const props = defineProps(["activePage"])
</script>

<style scoped>

</style>