<template>
  <div class="col-md-5 mt-4">
    <div class="card h-100 mb-4">
      <div class="card-header pb-0 px-3">
        <div class="row">
          <div class="col-md-6">
            <h6 class="mb-0">STOK AZALAN ÜRÜNLER</h6>
          </div>
          <div class="col-md-6 d-flex justify-content-start justify-content-md-end align-items-center">
            <i class="material-icons me-2 text-lg">date_range</i>
            <small>01 - 07 Ekim 2022</small>
          </div>
        </div>
      </div>
      <div class="card-body pt-4 p-3">
        <ul class="list-group">

          <li v-for="item in haftalikSatis"
              class="list-group-item border-0 d-flex justify-content-between ps-0 mb-2 border-radius-lg">
            <div class="d-flex align-items-center">
              <button
                  class="btn btn-icon-only btn-rounded btn-outline-danger mb-0 me-3 p-3 btn-sm d-flex align-items-center justify-content-center">
                <i class="material-icons text-lg">expand_more</i></button>
              <div class="d-flex flex-column">
                <h6 class="mb-1 text-dark text-sm">{{ item[0].toUpperCase() }}</h6>
                <span class="text-xs">27 March 2020, at 12:30 PM</span>
              </div>
            </div>
            <div class="d-flex align-items-center text-danger text-gradient text-sm font-weight-bold">
              - $ {{ item[1] }}
            </div>
          </li>

        </ul>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ProductSummary"
}
</script>

<style scoped>

</style>