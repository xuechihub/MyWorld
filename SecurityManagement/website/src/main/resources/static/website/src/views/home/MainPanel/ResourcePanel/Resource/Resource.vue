<template>
    <left-right-layout>
        <resource-tree slot="left"></resource-tree>
        <resource-response slot="right"></resource-response>
    </left-right-layout>
</template>

<script>
    import LeftRightLayout from '../../../../../components/Layout/LeftRightLayout';
    import ResourceTree from './ResourceTree';
    import ResourceResponse from './ResourceResponse';
    export default {
        name: 'Resource',
        components: {
            LeftRightLayout: LeftRightLayout,
            ResourceTree: ResourceTree,
            ResourceResponse: ResourceResponse
        }
    };
</script>

<style scoped>

</style>
