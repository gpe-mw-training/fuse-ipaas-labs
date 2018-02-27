ansible-playbook -i ${TARGET_HOST}, ./configs/ocp-workloads/ocp-workload.yml \
                 -e"ansible_ssh_private_key_file=~/.ssh/keytoyourhost.pem" \
                 -e"ansible_ssh_user=opentlc-mgr" \
                    -e"ANSIBLE_REPO_PATH=`pwd`" \
                    -e"ocp_username=${OCP_USERNAME}" \
                    -e"ocp_workload=${WORKLOAD}" \
                    -e"guid=${GUID}" \
                    -e"ocp_user_needs_quota=true" \
                    -e"ocp_apps_domain=apps.dev37.openshift.opentlc.com" \
                    -e"ACTION=create" \
